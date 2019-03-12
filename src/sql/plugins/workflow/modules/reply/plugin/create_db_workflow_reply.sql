--
-- Table structure for table task_reply_cf
--
DROP TABLE IF EXISTS workflow_task_reply_cf;
CREATE TABLE workflow_task_reply_cf(
  id_task INT DEFAULT 0 NOT NULL,
  is_mandatory INT DEFAULT 0,
  is_rich_text INT DEFAULT 0,
  title VARCHAR(255) DEFAULT '',
  default_message LONG VARCHAR,
  PRIMARY KEY (id_task)
);

--
-- Table structure for table task_reply
--
DROP TABLE IF EXISTS workflow_task_reply;
CREATE TABLE workflow_task_reply(
  id_history INT DEFAULT 0 NOT NULL,
  id_task INT DEFAULT 0 NOT NULL,
  message LONG VARCHAR,
  PRIMARY KEY (id_history, id_task)
);
