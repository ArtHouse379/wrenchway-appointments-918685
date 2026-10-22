package lukasztruckfix.garage.wrenchwayappointments.data.model

data class ServiceModel(
    val id: Int,
    val name: String,
    val description: String,
    val serviceCategory: ServiceCategory,
    val pricePerHour: Double,
    val imageUrl: String,

    // Briefly describe what the service includes - short phrases, not paragraphs.
    val includedItems: List<String> = emptyList(),
)