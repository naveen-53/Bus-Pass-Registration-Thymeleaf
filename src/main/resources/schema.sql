CREATE TABLE IF NOT EXISTS bus_pass (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  pass_type VARCHAR(50),
  start_date DATE,
  end_date DATE
);
