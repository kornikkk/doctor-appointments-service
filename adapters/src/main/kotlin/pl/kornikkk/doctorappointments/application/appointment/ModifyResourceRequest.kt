package pl.kornikkk.doctorappointments.application.appointment

data class ModifyResourceRequest(val op: String, val path: String, val value: Any) {
}