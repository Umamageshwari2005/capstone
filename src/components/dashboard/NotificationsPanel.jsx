import "../../css/notification.css";

export default function NotificationsPanel({ notifications }) {

    return (

        <div className="dashboard-card">

            <div className="card-header">

                <h2>Notifications</h2>

            </div>

            {

                notifications.length === 0 ?

                    <div className="empty">

                        No Notifications

                    </div>

                    :

                    notifications.map((notification) => (

                        <div
                            key={notification.notificationId}
                            className="notification-item">

                            <div>

                                <h4>

                                    {notification.message}

                                </h4>

                                <small>

                                    {notification.createdAt}

                                </small>

                            </div>

                        </div>

                    ))

            }

        </div>

    );

}
