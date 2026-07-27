INSERT INTO verification_records (id, learner_name, learner_email, skill_name, provider, status, assessment_score, notes, created_at)
VALUES
(1, 'Student User', 'student@skillswap.ca', 'HTML and CSS', 'SkillSwap Academy', 'APPROVED', 88, 'Completed the beginner web design assessment.', CURRENT_TIMESTAMP),
(2, 'Alex Morgan', 'alex@example.com', 'Conversational Spanish', 'SkillSwap Academy', 'PENDING', 72, 'Awaiting instructor review.', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;
SELECT setval(pg_get_serial_sequence('verification_records', 'id'), GREATEST((SELECT MAX(id) FROM verification_records), 1));
