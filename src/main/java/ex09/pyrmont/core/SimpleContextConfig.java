/**
 * copied from ex08.pyrmont.core.SimpleContextConfig
 */
package ex09.pyrmont.core;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

public class SimpleContextConfig implements LifecycleListener {

    public void lifecycleEvent(LifecycleEvent event) {
        if (Lifecycle.START_EVENT.equals(event.getType())) {  //这个事件检查只有一个类型指标？
            Context context = (Context) event.getLifecycle();   //直接强转为context？
            context.setConfigured(true);
        }
    }
}