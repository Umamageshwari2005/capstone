package com.hexaware.career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestDbInitializer {

    @Autowired
    private JdbcTemplate jdbc;

    public void init() {
        jdbc.execute("SET FOREIGN_KEY_CHECKS = 0");

        jdbc.execute("INSERT INTO users (user_id, full_name, email, password, role, cgpa, passed_out_year) " +
                "VALUES (1, 'Uma', 'uma@gmail.com', 'uma123', 'EMPLOYER', 0.0, 0) " +
                "ON DUPLICATE KEY UPDATE full_name='Uma'");

        jdbc.execute("INSERT INTO jobs (job_id, job_title, company_name, employer_id, status, salary) " +
                "VALUES (1, 'Java Developer', 'Hexaware', 1, 'Active', 0.0) " +
                "ON DUPLICATE KEY UPDATE job_title='Java Developer'");

        jdbc.execute("INSERT INTO applications (application_id, status, user_id, job_id) " +
                "VALUES (1, 'Applied', 1, 1) " +
                "ON DUPLICATE KEY UPDATE status='Applied'");

        jdbc.execute("INSERT INTO resumes (resume_id, resume_name, file_name, file_path, user_id) " +
                "VALUES (1, 'Java_Resume.pdf', 'Java_Resume.pdf', 'uploads/Java_Resume.pdf', 1) " +
                "ON DUPLICATE KEY UPDATE resume_name='Java_Resume.pdf'");

        jdbc.execute("INSERT INTO skills (skill_id, skill_name, user_id) " +
                "VALUES (1, 'Java', 1) " +
                "ON DUPLICATE KEY UPDATE skill_name='Java'");

        jdbc.execute("INSERT INTO notifications (notification_id, message, type, is_read, user_id) " +
                "VALUES (1, 'Your application has been received.', 'Status Update', false, 1) " +
                "ON DUPLICATE KEY UPDATE message='Your application has been received.'");

        jdbc.execute("INSERT INTO job_preferences (preference_id, preferred_role, preferred_location, user_id, expected_salary) " +
                "VALUES (1, 'Developer', 'Chennai', 1, 0.0) " +
                "ON DUPLICATE KEY UPDATE preferred_role='Developer'");

        jdbc.execute("SET FOREIGN_KEY_CHECKS = 1");
    }
}
