package repository;

import dto.Store;

public class StoreRepository {

    private static StoreRepository storeRepository;

    public StoreRepository() {}

    public static StoreRepository getInstance() {
        if (storeRepository == null) {
            storeRepository = new StoreRepository();
        }
        return storeRepository;
    }

}
