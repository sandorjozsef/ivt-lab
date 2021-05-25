package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;
  private GT4500 ship;

  @BeforeEach
  public void init(){

    mockTS1 = mock(TorpedoStore.class);
    mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success_State_1(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    
  }

  @Test
  public void fireTorpedo_Single_Success_State_2(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    
  }

  @Test
  public void fireTorpedo_Single_Success_Interaction(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Fail_State(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Fail_Interaction(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_whenPrimaryFirst_State_1(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedo_Single_Success_whenPrimaryFirst_State_2(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Fail(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
   
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_Fail_2(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);
   
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_Fail_3(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(false);
   
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireLaser_Fail(){
    // Arrange
   
   
    // Act
    boolean result = ship.fireLaser(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

}
