package th.ac.kku.cis.mobileapp.finalapplication.model

class ToDoAdd {
    companion object Factory {
        fun create(): ToDoAdd = ToDoAdd()
    }

    var name: String? = null
    var type : String? = null
    var detail : String? = null
    var id : String? = null
}
