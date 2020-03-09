package pl.kornikkk.doctorappointments.application.controller.request

data class ModifyResourceRequest(val op: String, val path: String, val value: Any) {
}