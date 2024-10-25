package com.jorge.docker_manager.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.Container;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;


class DockerServiceTest {

    @Mock
    private DockerClient dockerClient;

    @Mock
    private ListContainersCmd listContainersCmd;

    @InjectMocks
    private DockerService dockerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testa se o método consulta os containers quando recebe showAll=true")
    public void testListContainers() {
        List<Container> mockContainers = List.of(new Container());
        when(dockerClient.listContainersCmd()).thenReturn(listContainersCmd);
        when(listContainersCmd.withShowAll(true)).thenReturn(listContainersCmd);
        when(listContainersCmd.exec()).thenReturn(mockContainers);

        List<Container> result = dockerService.listContainers(true);

        assertEquals(mockContainers, result);
        verify(dockerClient).listContainersCmd();
        verify(listContainersCmd).withShowAll(true);
        verify(listContainersCmd).exec();
    }
}
