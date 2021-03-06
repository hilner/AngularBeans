package angularBeans.demoApp.ngbeans;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import angularBeans.api.AngularBean;
import angularBeans.context.NGSessionScoped;
import angularBeans.demoApp.domain.NotificationMessage;
import angularBeans.realtime.RealTimeClient;
import angularBeans.realtime.RealTimeMessage;

@AngularBean
@NGSessionScoped
public class NotificationsService implements Serializable {

	@Inject
	RealTimeClient client;

	public void fireNotification(@Observes NotificationMessage message) {

		RealTimeMessage rtMessage = new RealTimeMessage().set("message",
				message);

		if (message.isBroadcast()) {
			client.broadcast("notificationChannel", rtMessage, false);
		} else {
			client.publish("notificationChannel", rtMessage);
		}

	}

}
