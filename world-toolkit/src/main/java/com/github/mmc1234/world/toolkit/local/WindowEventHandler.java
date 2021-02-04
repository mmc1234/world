package com.github.mmc1234.world.toolkit.local;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.message.EditableQueryEvent;
import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.gui.ViewUtils;
import com.google.common.collect.ImmutableList;

public class WindowEventHandler implements IWindowEventHandler {
  private EditableQueryEvent queryEvent = new EditableQueryEvent();
  @Override
  public void handleAcceptFile(Window window, ImmutableList<String> files) {
    double hx = window.getCursor().x, hy = window.getCursor().y;
    View result = window.getRootView().onHit(window, hx, hy);
    if (result != null) {
      result.onDropFile(window, files);
    }
  }

  @Override
  public void handleButton(Window window, ActionType actionType, ButtonType buttonType, int mods) {
    if (window.getRootView() != null) {
      double hx = window.getCursor().x, hy = window.getCursor().y;
      View result = window.getRootView().onHit(window, hx, hy);
      View lastHoldView = window.holdView;
      if (result != null) {
        if (actionType == ActionType.Press) {
          window.holdView = result;
          window.clickTime = GLFW.glfwGetTimerValue();
        } else if (actionType == ActionType.Release) {
          window.holdView = null;
          if (result == lastHoldView || lastHoldView == null) {
            this.queryEvent.setEvent(new ClickEvent(window, hx, hy, buttonType));
            window.context.getEventBus().post(this.queryEvent);
            if (!this.queryEvent.getEvent().isCancel()) {
              result.onClick((ClickEvent) this.queryEvent.getEvent());
            }
          } else {
            this.queryEvent.setEvent(new CancelClickEvent(window, hx, hy, buttonType, lastHoldView));
            window.context.getEventBus().post(this.queryEvent);
            if (!this.queryEvent.getEvent().isCancel()) {result.onCancelClick((CancelClickEvent) this.queryEvent.getEvent());
            }
          }
        } else {
          this.queryEvent.setEvent(new LongClickEvent(window, hx, hy, buttonType, lastHoldView));
          window.context.getEventBus().post(this.queryEvent);
          result.onLongClick((LongClickEvent) this.queryEvent.getEvent());
        }
        result.onButton(window, actionType, buttonType, hx, hy, mods);
      }
    }
  }

  @Override
  public void handleClose(Window window) {
    
  }

  @Override
  public void handleContentScale(Window window, double xscale, double yscale) {
    
  }

  @Override
  public void handleEnter(Window window, boolean entered) {

  }

  @Override
  public void handleFocus(Window window, boolean focused) {
    window.isFocus = focused;
  }

  @Override
  public void handleFrameBufferResize(Window window, int width, int height) {

  }

  @Override
  public void handleIconify(Window window, boolean iconified) {

  }

  @Override
  public void handleInput(Window window, char ch) {
    double hx = window.getCursor().x, hy = window.getCursor().y;
    if(window.focusView!=null) {
      window.focusView.onInput(window, ch, hx, hy);
    }
  }

  @Override
  public void handleInputMods(Window window, char ch, int mods) {

  }

  @Override
  public void handleKey(Window window, ActionType actionType, int key, int scancode, int mods) {
    double hx = window.getCursor().x, hy = window.getCursor().y;
    if (actionType == ActionType.Release) {
      window.keyTime = -1;
    } else if (actionType == ActionType.Press) {
      window.keyTime = GLFW.glfwGetTimerValue();
    }
    if (window.focusView != null) {
      window.focusView.onKey(window, actionType, hx, hy, key, GLFW.glfwGetTimerValue() - window.keyTime, scancode, mods);
    }
  }

  @Override
  public void handleMaximize(Window window, boolean maximized) {

  }

  @Override
  public void handleMoveCursor(Window window, double x, double y) {
    // 这里需要处理视图的悬浮
    window.cursor.x = x;
    window.cursor.y = y;
    View lastStayView = window.getStayView();
    double hx = window.getCursor().x, hy = window.getCursor().y;
    View result = window.getRootView() == null ? null : window.getRootView().onHit(window, hx, hy);
    if(lastStayView != result) {
      window.stayView = result;
      if(lastStayView!=null) {
        lastStayView.onExit(window);
      }
      if(result!=null) {
        result.onEnter(window);
      }
    }
    
  }

  @Override
  public void handleRefresh(Window window) {

  }

  @Override
  public void handleResize(Window window, double width, double height) {
    if(window.width != width | window.height != height) {
      if(window.getRootView()!=null) {
        Dimension2D size = new Dimension2D(width, height), pos = new Dimension2D(0, 0);
        window.rootView.calculate(size, pos);
        ViewUtils.reshape(window.rootView, pos.x, pos.y, size.x, size.y);
      }
    }
    window.width = (int) width;
    window.height = (int) height;
  }

  @Override
  public void handleScroll(Window window, double offsetX, double offsetY) {

  }

}
