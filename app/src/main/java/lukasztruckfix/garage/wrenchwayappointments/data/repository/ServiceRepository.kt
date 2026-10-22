package lukasztruckfix.garage.wrenchwayappointments.data.repository

import lukasztruckfix.garage.wrenchwayappointments.data.model.ServiceCategory
import lukasztruckfix.garage.wrenchwayappointments.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ServiceRepository {
    private val services: List<ServiceModel> = listOf(
        ServiceModel(
            id = 1,
            name = "Routine Car Service",
            description = "Old engine oil is drained and replaced with the correct grade for your vehicle. A fresh oil filter is fitted to help protect internal engine components. Fluid levels and visible wear items are checked before the vehicle is returned.",
            serviceCategory = ServiceCategory.ROUTINE_MAINTENANCE,
            pricePerHour = 55.0,
            imageUrl = "https://images.pexels.com/photos/33814735/pexels-photo-33814735.jpeg",
            includedItems = listOf("Engine oil replacement", "New oil filter", "Fluid level check"),
        ),
        ServiceModel(
            id = 2,
            name = "Vehicle Safety Inspection",
            description = "A technician checks key safety and wear items across your vehicle. The inspection covers lights, fluids, tyres, brakes, and visible engine-bay components. You receive clear findings and advice on any work that should be scheduled.",
            serviceCategory = ServiceCategory.ROUTINE_MAINTENANCE,
            pricePerHour = 48.0,
            imageUrl = "https://images.pexels.com/photos/20041492/pexels-photo-20041492.jpeg",
            includedItems = listOf("Safety component check", "Fluid inspection", "Condition report"),
        ),
        ServiceModel(
            id = 3,
            name = "Engine Diagnostic",
            description = "Diagnostic equipment is used to investigate warning lights, poor running, or unusual engine behaviour. A technician combines fault-code results with focused checks under the bonnet. The findings identify likely causes and the recommended next repair.",
            serviceCategory = ServiceCategory.DIAGNOSTICS,
            pricePerHour = 72.0,
            imageUrl = "https://images.pexels.com/photos/13065692/pexels-photo-13065692.jpeg",
            includedItems = listOf("Fault-code scan", "Engine system checks", "Repair recommendation"),
        ),
        ServiceModel(
            id = 4,
            name = "Electrical Diagnostic",
            description = "Electrical testing locates faults affecting starting, charging, lighting, or vehicle controls. The technician checks relevant circuits, connections, and live diagnostic data. Results are explained before replacement parts or further labour are approved.",
            serviceCategory = ServiceCategory.DIAGNOSTICS,
            pricePerHour = 78.0,
            imageUrl = "https://images.pexels.com/photos/8985448/pexels-photo-8985448.jpeg",
            includedItems = listOf("System scan", "Circuit testing", "Fault report"),
        ),
        ServiceModel(
            id = 5,
            name = "Engine Repair",
            description = "Worn or failed engine components are inspected and repaired where practical. The work is planned around the confirmed fault, with suitable replacement parts agreed in advance. The repaired area is tested for correct operation before collection.",
            serviceCategory = ServiceCategory.MECHANICAL_REPAIR,
            pricePerHour = 85.0,
            imageUrl = "https://images.pexels.com/photos/8478245/pexels-photo-8478245.jpeg",
            includedItems = listOf("Fault inspection", "Component repair", "Post-repair test"),
        ),
        ServiceModel(
            id = 6,
            name = "Exhaust Repair",
            description = "The exhaust system is checked for leaks, damage, loose mountings, and excessive corrosion. Repairable sections are secured or sealed, while failed parts can be replaced with approval. The completed system is checked for noise and gas leaks.",
            serviceCategory = ServiceCategory.MECHANICAL_REPAIR,
            pricePerHour = 68.0,
            imageUrl = "https://images.pexels.com/photos/34011409/pexels-photo-34011409.jpeg",
            includedItems = listOf("Exhaust inspection", "Leak repair", "Mounting check"),
        ),
        ServiceModel(
            id = 7,
            name = "Brake Pad Replacement",
            description = "Worn brake pads are removed and replaced with parts suited to your vehicle. Brake discs, callipers, and visible hydraulic components are inspected during the work. The braking system is checked for safe, even operation after fitting.",
            serviceCategory = ServiceCategory.TYRES_BRAKES,
            pricePerHour = 70.0,
            imageUrl = "https://images.pexels.com/photos/6870304/pexels-photo-6870304.jpeg",
            includedItems = listOf("Brake pad fitting", "Disc inspection", "Brake operation check"),
        ),
        ServiceModel(
            id = 8,
            name = "Tyre Replacement",
            description = "A worn or damaged tyre is removed and a correctly sized replacement is fitted. The wheel is balanced and the tyre pressure is set to the vehicle specification. Tread and visible condition on the remaining tyres are also checked.",
            serviceCategory = ServiceCategory.TYRES_BRAKES,
            pricePerHour = 45.0,
            imageUrl = "https://images.pexels.com/photos/5260115/pexels-photo-5260115.jpeg",
            includedItems = listOf("Tyre fitting", "Wheel balancing", "Pressure check"),
        ),
        ServiceModel(
            id = 9,
            name = "Car Battery Replacement",
            description = "The existing battery and charging output are tested before replacement. A compatible battery is selected for the vehicle's electrical requirements and fitted securely. Starting performance and terminal connections are checked after installation.",
            serviceCategory = ServiceCategory.PARTS_ACCESSORIES,
            pricePerHour = 52.0,
            imageUrl = "https://images.pexels.com/photos/5572275/pexels-photo-5572275.jpeg",
            includedItems = listOf("Battery test", "Compatible battery selection", "Battery fitting"),
        ),
        ServiceModel(
            id = 10,
            name = "Car Part Fitting",
            description = "A suitable replacement part is selected with attention to vehicle compatibility and expected use. The component is installed securely using the correct fixings and connections. Its operation is tested before the vehicle is handed back.",
            serviceCategory = ServiceCategory.PARTS_ACCESSORIES,
            pricePerHour = 65.0,
            imageUrl = "https://images.pexels.com/photos/8478254/pexels-photo-8478254.jpeg",
            includedItems = listOf("Compatibility check", "Part fitting", "Operation test"),
        ),
    )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}
