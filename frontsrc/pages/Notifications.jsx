import { useEffect, useState } from "react";

import NotificationService from "../services/NotificationService";

import "../css/notifications.css";

export default function Notifications() {

    const userId = sessionStorage.getItem("userId");

    const [notifications, setNotifications] = useState([]);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadNotifications();

    }, []);

    async function loadNotifications() {

        try {

            const response =
                await NotificationService.getNotificationsByUser(userId);

            setNotifications(response.data);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    }

    if (loading) {

        return (

            <div className="loading">

                <h2>Loading Notifications...</h2>

            </div>

        );

    }

    return (

        <div
    className="notifications-page"
    style={{
        background: "linear-gradient(135deg,#5EEAD4,#14B8A6)",
        minHeight: "100vh",
        padding: "30px",
        borderRadius: "20px"
    }}
>

            <div className="notifications-header">

                <h1>

                    Notifications

                </h1>

            </div>

            {

                notifications.length === 0 ?

                    <div className="empty-box">

                        No Notifications Available

                    </div>

                    :

                    notifications.map(notification => (

                        <div
                            key={notification.notificationId}
                            className={`notification-card ${notification.read ? "read" : "unread"}`}
                        >

                            <div className="notification-icon">

                                🔔

                            </div>

                            <div className="notification-content">

                                <h3>

                                    {notification.type}

                                </h3>

                                <p>

                                    {notification.message}

                                </p>

                                <small>

                                    {notification.createdAt}

                                </small>

                            </div>

                            <div>

                                {

                                    notification.read ?

                                        <span className="read-badge">

                                            Read

                                        </span>

                                        :

                                        <span className="unread-badge">

                                            New

                                        </span>

                                }

                            </div>

                        </div>

                    ))

            }

        </div>

    );

}
