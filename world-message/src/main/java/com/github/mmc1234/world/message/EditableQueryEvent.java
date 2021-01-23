package com.github.mmc1234.world.message;

import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
public class EditableQueryEvent implements QueryEvent {
  public Event event;
  @Override
  public Event getEvent() {
    return event;
  }

}
