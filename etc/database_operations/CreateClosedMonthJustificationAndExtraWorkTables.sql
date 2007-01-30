CREATE TABLE `CLOSED_MONTH_JUSTIFICATION` (
  `ID_INTERNAL` int(11) unsigned NOT NULL auto_increment,
  `KEY_ASSIDUOUSNESS_CLOSED_MONTH` int(11) NOT NULL default '1',
  `KEY_JUSTIFICATION_MOTIVE` int(11) NOT NULL default '1',
  `JUSTIFICATION_DURATION` bigint(20) DEFAULT NULL,
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL default '1',
  PRIMARY KEY  (`ID_INTERNAL`)
) ENGINE=InnoDB;

CREATE TABLE `ASSIDUOUSNESS_EXTRA_WORK` (
  `ID_INTERNAL` int(11) unsigned NOT NULL auto_increment,
  `NIGHT_BALANCE` bigint(20) DEFAULT NULL,
  `FIRST_LEVEL_BALANCE` bigint(20) DEFAULT NULL,
  `SECOND_LEVEL_BALANCE` bigint(20) DEFAULT NULL,
  `UNJUSTIFIED` bigint(20) DEFAULT NULL,
  `KEY_WORK_SCHEDULE_TYPE` int(11) NOT NULL default '1',
  `KEY_ASSIDUOUSNESS_CLOSED_MONTH` int(11) NOT NULL default '1',
  `KEY_ROOT_DOMAIN_OBJECT` int(11) NOT NULL default '1',
  PRIMARY KEY  (`ID_INTERNAL`)
) ENGINE=InnoDB;