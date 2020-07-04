package com.network;

import com.model.ITrip;
import com.model.Trip;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class NetworkManagerTest {

    INetworkManager networkManager;
    ITrip trip;
    @Before
    public void setUp() {
        trip = new Trip("Hamburg","Berlin","Now","3");
        networkManager = Mockito.mock(NetworkManager.class);
    }

    @Test
    public void getUseCase() {
        List<ITrip> tripList = new ArrayList<>();
        Mockito.when(networkManager.get("http://10.0.2.2:8080/")).thenReturn(tripList);

    }
    @Test
    public void getOneTripUseCase() {
        List<ITrip> tripList = new ArrayList<>();
        Mockito.when(networkManager.get("http://10.0.2.2:8080/1")).thenReturn(tripList);

    }

    @Test
    public void postUseCase() {
        Mockito.when(networkManager.post("http://10.0.2.2:8080/",trip)).thenReturn(true);
    }

    @Test
    public void putUseCase() {
        Mockito.when(networkManager.put("http://10.0.2.2:8080/",trip)).thenReturn(true);
    }

    @Test
    public void deleteUseCase() {
        Mockito.when(networkManager.delete("http://10.0.2.2:8080/1")).thenReturn(true);
    }

    @Test
    public void postObjectNull() {
        Mockito.when(networkManager.post("http://10.0.2.2:8080/",null)).thenReturn(false);
    }

    @Test
    public void putUseObjectNull() {
        Mockito.when(networkManager.put("http://10.0.2.2:8080/",null)).thenReturn(false);
    }

    @Test
    public void deleteUseNumberNotInLIst() {
        Mockito.when(networkManager.delete("http://10.0.2.2:8080/100")).thenReturn(false);
    }
}