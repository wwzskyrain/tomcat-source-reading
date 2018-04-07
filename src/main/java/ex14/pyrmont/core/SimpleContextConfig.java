package ex14.pyrmont.core;

// a copy of ex13.pyrmont.core.SimpleContextConfig

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

public class SimpleContextConfig implements LifecycleListener { //用事件的方式实现配置信息的添加；

    public void lifecycleEvent(LifecycleEvent event) {
        if (Lifecycle.START_EVENT.equals(event.getType())) {
            Context context = (Context) event.getLifecycle();
            context.setConfigured(true);
        }
    }
}