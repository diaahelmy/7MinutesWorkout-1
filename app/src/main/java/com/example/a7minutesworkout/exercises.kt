package com.example.a7minutesworkout



class exercises(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var selected:Boolean,
    private var complete:Boolean
    ){

   fun get_id(): Int {
       return id
   }
    fun set_id(id: Int){
        this.id=id
    }
    fun get_name(): String {
        return name
    }
    fun set_name(name: String){
        this.name=name
    }
    fun get_image(): Int {
        return image
    }
    fun set_image(image: Int){
        this.image=image
    }
    fun get_selected(): Boolean {
        return selected
    }
    fun set_selected(selected: Boolean){
        this.selected=selected
    }
    fun get_complete(): Boolean {
        return complete
    }
    fun set_complete(complete: Boolean){
        this.complete=complete
    }
}