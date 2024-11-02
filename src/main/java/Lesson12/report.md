### Было

```kotlin
class GetAddressesUseCase @Inject constructor() {
    suspend operator fun invoke(query: String): Result<List<CityAddress>> {
        val dadataRepository = DadataRepository()
        return dadataRepository.getAddresses(query)
    }
}

class DadataRepository {
    suspend fun getAddresses(query: String): Result<List<CityAddress>> {
        //...
    }

    suspend fun getOrganizations(query: String): Result<List<OrganizationInfoFromDadata>> {
        //...
    }
}
```

### Стало

```kotlin
class GetAddressesUseCase @Inject constructor(
    private val dadataRepository: DadataRepository
) {
    suspend operator fun invoke(query: String) = dadataRepository.getAddresses(query)
}

interface DadataRepository {
    suspend fun getAddresses(query: String): Result<List<CityAddress>>
    suspend fun getOrganizations(query: String): Result<List<OrganizationInfoFromDadata>>
}

class DadataRepositoryImpl @Inject constructor(
    private val dadataService: Provider<DadataService>
) : DadataRepository {
    override suspend fun getAddresses(query: String): Result<List<CityAddress>> {
        //...
    }

    override suspend fun getOrganizations(query: String): Result<List<OrganizationInfoFromDadata>> {
       //...
    }
}
```