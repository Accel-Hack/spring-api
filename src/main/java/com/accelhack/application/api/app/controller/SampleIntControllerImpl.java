// package com.accelhack.application.api.app.controller;
//
// import com.accelhack.accelparts.Request;
// import com.accelhack.accelparts.ResponseSet;
// import com.accelhack.accelparts.response.ListResponse;
// import com.accelhack.application.api.app.model.Sample2;
// import com.accelhack.application.api.app.model.SampleSelector;
// import com.accelhack.application.api.app.transaction.SampleTransaction;
// import com.accelhack.application.api.shared.controller.base.InternalController;
// import com.accelhack.application.api.shared.functional.ParameterizedApi;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
//
// @Controller
// @RequiredArgsConstructor
// public class SampleIntControllerImpl extends InternalController implements SampleController {
// private final SampleTransaction sampleTransaction;
//
// @Override
// public ResponseEntity<ResponseSet<Sample2>> get(Request<Sample2> sampleRequest) {
// ParameterizedApi<Sample2, Sample2> callable = sampleTransaction::get;
// return execute(sampleRequest, callable);
// }
////
//// @Override
//// public ResponseEntity<ResponseSet<ListResponse<Sample2>>> search(
//// Request<SampleSelector> sampleRequest) {
//// ParameterizedApi<SampleSelector, ListResponse<Sample2>> callable = sampleTransaction::search;
//// return execute(sampleRequest, callable);
//// }
////
//// @Override
//// public ResponseEntity<ResponseSet<Sample2>> add(Request<Sample2> sampleRequest) {
//// ParameterizedApi<Sample2, Sample2> callable = sampleTransaction::add;
//// return execute(sampleRequest, callable);
//// }
////
//// @Override
//// public ResponseEntity<ResponseSet<Sample2>> edit(Request<Sample2> sampleRequest) {
//// ParameterizedApi<Sample2, Sample2> callable = sampleTransaction::edit;
//// return execute(sampleRequest, callable);
//// }
////
//// @Override
//// public ResponseEntity<ResponseSet<Sample2>> remove(Request<Sample2> sampleRequest) {
//// ParameterizedApi<Sample2, Sample2> callable = sampleTransaction::remove;
//// return execute(sampleRequest, callable);
//// }
// }
