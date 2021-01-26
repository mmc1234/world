package java.net;

import java.util.Hashtable;

import com.github.mmc1234.world.message.Event;
import com.github.mmc1234.world.toolkit.gui.View;
import com.google.common.eventbus.Subscribe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class HandlerHelper {
  public static Hashtable<String, URLStreamHandler> get() {
    return URL.handlers;
  }
  
  
  
  

  public void onClick(double x,double y) {
    
  }
}
