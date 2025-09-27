-- 初期データ
INSERT INTO point_transactions (user_id, points, type, description, created_at) VALUES
('user1', 1000, 'ADD', '初回登録ボーナス', '2024-01-01 10:00:00'),
('user1', 500, 'ADD', '商品購入', '2024-01-02 14:30:00'),
('user1', 200, 'USE', 'クーポン利用', '2024-01-03 16:45:00'),
('user2', 800, 'ADD', '初回登録ボーナス', '2024-01-01 11:00:00'),
('user2', 300, 'ADD', '商品購入', '2024-01-04 09:15:00');