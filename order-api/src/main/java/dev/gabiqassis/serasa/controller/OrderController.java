package dev.gabiqassis.serasa.controller;

import dev.gabiqassis.serasa.domain.request.OrderCreaterRequest;
import dev.gabiqassis.serasa.domain.request.OrderUpdateRequest;
import dev.gabiqassis.serasa.domain.response.OrderResponse;
import dev.gabiqassis.serasa.domain.response.OrderUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.gabiqassis.serasa.constants.PathConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

@RequestMapping(produces = APPLICATION_JSON_VALUE)
public interface OrderController {

    @Operation(
            summary = "Find all orders",
            tags = {"orders"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @GetMapping(ORDER_V1)
    ResponseEntity<List<OrderResponse>> findAll();

    @Operation(
            summary = "Create an order",
            tags = {"orders"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @PostMapping(ORDER_V1)
    ResponseEntity<OrderResponse> create(@RequestBody @Valid OrderCreaterRequest orderCreateRequest);

    @Operation(
            summary = "Find an order by id",
            tags = {"orders"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @GetMapping(ORDER_BY_ID)
    ResponseEntity<OrderResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Update an order by id",
            tags = {"orders"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @PutMapping(ORDER_BY_ID)
    ResponseEntity<OrderUpdateResponse> update(@PathVariable Long id, @Valid @RequestBody OrderUpdateRequest orderUpdateRequest);

    @Operation(
            summary = "Find orders by userId",
            tags = {"orders"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @PutMapping(ORDER_BY_USER_ID)
    ResponseEntity<List<OrderResponse>> findByUserId(@PathVariable Long userId);

    @Operation(
            summary = "Delete an order by id",
            tags = {"orders"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @DeleteMapping(ORDER_BY_ID)
    ResponseEntity<String> deleteById(@PathVariable Long id);
}

