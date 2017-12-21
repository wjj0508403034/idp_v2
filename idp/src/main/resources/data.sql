-- Insert admin
INSERT INTO admin_account(`id`,`first_name`,`last_name`,`email`,`password`,`locked`,`version`,`create_time`,`update_time`)
SELECT * FROM (
	SELECT 1 as id, 
	'admin' as first_name, 
	'admin' as last_name, 
	'admin' as email, 
	'$2a$10$XgnLxmtscVrhBS0ksmVLZ.iJQW9LH9.aZ.duoJHlIEdsJYD75Y5za' as password, --rawPassword:1234
	0 as locked, 
	1 as version, 
	NOW() as create_time, 
	NOW() as update_time
	) AS tmp
WHERE NOT EXISTS (
    SELECT id FROM admin_account WHERE id = 1
) LIMIT 1;