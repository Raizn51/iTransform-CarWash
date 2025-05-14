package com.carwash.payment_service.controller;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.carwash.payment_service.service.BraintreeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * Controller to manage payment-related endpoints.
 * This includes payment processing, client token generation, and receipt downloading.
 */
@RestController
@RequestMapping("/api/v1/pay")
@Tag(name = "Payment Controller", description = "Handles all payment processing, receipt generation, and payment status queries.")
public class PaymentController {

    @Autowired
    private BraintreeService braintreeService;

    /**
     * Endpoint to generate a client token for the frontend to use in the payment process.
     * This token will be used to securely handle payments using the Braintree gateway.
     *
     * @return a client token for payment processing.
     */
    @GetMapping("/client-token")
    @Operation(
            summary = "Generate Client Token",
            description = "Generates a secure client token required by Braintree to initiate the payment process."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client token successfully generated."),
            @ApiResponse(responseCode = "500", description = "Internal server error while generating client token.")
    })
    public ResponseEntity<String> getClientToken() {
        return ResponseEntity.ok(braintreeService.generateClientToken());
    }

    /**
     * Endpoint to process a payment using the provided nonce and order ID.
     * This method interacts with the Braintree gateway to perform a transaction.
     *
     * @param nonce    a unique payment nonce generated on the frontend.
     * @param orderId  the ID of the order being paid for.
     * @return a response containing the receipt or an error message.
     */
    @PostMapping("/process")
    @Operation(
            summary = "Process Payment",
            description = "Processes a payment using the provided nonce and order ID. Returns a receipt if successful."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment processed successfully, receipt generated."),
            @ApiResponse(responseCode = "400", description = "Payment processing failed, with error message."),
            @ApiResponse(responseCode = "500", description = "Internal server error during payment processing.")
    })
    public ResponseEntity<?> processPayment(
            @RequestParam("nonce") String nonce,
            @RequestParam("orderId") Long orderId) {

        Result<Transaction> result = braintreeService.processPayment(nonce, orderId);

        if (result.isSuccess()) {
            String receiptText = braintreeService.generateReceipt(result.getTarget(), orderId);
            return ResponseEntity.ok(receiptText);
        } else {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
    }

    /**
     * Endpoint to download a receipt for the given order ID.
     * The receipt is a PDF file generated after a successful payment.
     *
     * @param orderId  the ID of the order for which the receipt is generated.
     * @return a downloadable PDF receipt file.
     */
    @GetMapping("/receipt/{orderId}")
    @Operation(
            summary = "Download Receipt",
            description = "Generates and allows the user to download a receipt PDF for the payment of the specified order ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receipt downloaded successfully."),
            @ApiResponse(responseCode = "404", description = "Receipt not found for the given order ID.")
    })
    public ResponseEntity<?> downloadReceipt(@PathVariable Long orderId) {
        String fileName = "receipt_order_" + orderId + ".pdf";
        File file = new File("src/main/resources/receipts/" + fileName);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .body(resource);
    }
}
