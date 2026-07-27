INSERT INTO workshops (title, category, skill_level, instructor_name, instructor_email, duration_hours, capacity, description, created_at)
SELECT 'Beginner Web Design with HTML and CSS', 'TECHNOLOGY', 'BEGINNER', 'Maya Chen', 'instructor@skillswap.ca', 6, 20, 'Build and style a responsive personal webpage while learning semantic HTML and modern CSS fundamentals.', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM workshops WHERE title = 'Beginner Web Design with HTML and CSS');

INSERT INTO workshops (title, category, skill_level, instructor_name, instructor_email, duration_hours, capacity, description, created_at)
SELECT 'Phone Photography for Social Media', 'CREATIVE_ARTS', 'BEGINNER', 'Maya Chen', 'instructor@skillswap.ca', 4, 15, 'Learn composition, lighting, framing, and simple editing techniques using only a smartphone.', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM workshops WHERE title = 'Phone Photography for Social Media');

INSERT INTO workshops (title, category, skill_level, instructor_name, instructor_email, duration_hours, capacity, description, created_at)
SELECT 'Everyday Budgeting Basics', 'BUSINESS', 'BEGINNER', 'Jordan Blake', 'jordan@example.com', 3, 25, 'Create a realistic monthly budget, understand spending categories, and build a simple savings plan.', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM workshops WHERE title = 'Everyday Budgeting Basics');

INSERT INTO workshops (title, category, skill_level, instructor_name, instructor_email, duration_hours, capacity, description, created_at)
SELECT 'Conversational Spanish Practice', 'LANGUAGES', 'INTERMEDIATE', 'Sofia Reyes', 'sofia@example.com', 8, 12, 'Practice useful conversations, pronunciation, and vocabulary through guided partner activities.', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM workshops WHERE title = 'Conversational Spanish Practice');

INSERT INTO workshops (title, category, skill_level, instructor_name, instructor_email, duration_hours, capacity, description, created_at)
SELECT 'Meal Prep Fundamentals', 'LIFESTYLE', 'BEGINNER', 'Andre Lewis', 'andre@example.com', 5, 18, 'Plan balanced meals, organize a weekly prep routine, and learn safe food storage basics.', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM workshops WHERE title = 'Meal Prep Fundamentals');

INSERT INTO workshops (title, category, skill_level, instructor_name, instructor_email, duration_hours, capacity, description, created_at)
SELECT 'Java and Spring Boot Foundations', 'TECHNOLOGY', 'INTERMEDIATE', 'Maya Chen', 'instructor@skillswap.ca', 10, 16, 'Create a small Spring Boot application using controllers, templates, validation, and persistence.', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM workshops WHERE title = 'Java and Spring Boot Foundations');
