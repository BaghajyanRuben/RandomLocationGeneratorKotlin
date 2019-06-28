data class InstantImage(val url: String,
                        val id: String,
                        val name: String,
                        var dataTaken: Long = 0,
                        var latitude: Double = 0.0,
                        var longitude: Double = 0.0,
                        var distance: Float = 0.toFloat()) {

    override fun equals(other: Any?): Boolean {
        if (other is InstantImage) {
            return other.id == id
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
