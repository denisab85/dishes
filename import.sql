INSERT INTO `dishes`.`cuisine` (`id`,`name`,`parent_cuisine_id`)
	VALUES
		(0001, 'European', null),
		(0002, 'Asian', null),
		(0003, 'African', null),
		(0004, 'American', null),
		(0011, 'East European', 0001),
		(0012, 'Mediterranean', 0001),
		(0013, 'Middle Eastern', 0002),
		(0014, 'South-Eastern Asian', 0002),
		(0015, 'South Asian', 0002),
		(0016, 'North African', 0003),
		(0017, 'Central African', 0003),
		(0018, 'South African', 0003),
		(0019, 'North American', 0004),
		(0021, 'Latin American', 0004);