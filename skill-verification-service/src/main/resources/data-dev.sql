MERGE INTO verification_records (id, learner_name, learner_email, skill_name, provider, status, assessment_score, notes, created_at)
KEY(id) VALUES
(1, 'Student User', 'student@skillswap.ca', 'HTML and CSS', 'SkillSwap Academy', 'APPROVED', 88, 'Completed the beginner web design assessment.', CURRENT_TIMESTAMP),
(2, 'Alex Morgan', 'alex@example.com', 'Conversational Spanish', 'SkillSwap Academy', 'PENDING', 72, 'Awaiting instructor review.', CURRENT_TIMESTAMP);
ALTER TABLE verification_records ALTER COLUMN id RESTART WITH 100;
