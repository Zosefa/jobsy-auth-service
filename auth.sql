CREATE DATABASE auth_db;

CREATE TABLE pays (
  id SERIAL PRIMARY KEY,
  nom VARCHAR(150) NOT NULL,
  code_iso2 CHAR(2) UNIQUE NOT NULL,
  code_iso3 CHAR(3) UNIQUE
);

INSERT INTO pays (nom, code_iso2, code_iso3) VALUES
     ('Afghanistan','AF','AFG'),
     ('Afrique du Sud','ZA','ZAF'),
     ('Albanie','AL','ALB'),
     ('Algérie','DZ','DZA'),
     ('Allemagne','DE','DEU'),
     ('Andorre','AD','AND'),
     ('Angola','AO','AGO'),
     ('Antigua-et-Barbuda','AG','ATG'),
     ('Arabie saoudite','SA','SAU'),
     ('Argentine','AR','ARG'),
     ('Arménie','AM','ARM'),
     ('Australie','AU','AUS'),
     ('Autriche','AT','AUT'),
     ('Azerbaïdjan','AZ','AZE'),
     ('Bahamas','BS','BHS'),
     ('Bahreïn','BH','BHR'),
     ('Bangladesh','BD','BGD'),
     ('Barbade','BB','BRB'),
     ('Belgique','BE','BEL'),
     ('Belize','BZ','BLZ'),
     ('Bénin','BJ','BEN'),
     ('Bhoutan','BT','BTN'),
     ('Biélorussie','BY','BLR'),
     ('Bolivie','BO','BOL'),
     ('Bosnie-Herzégovine','BA','BIH'),
     ('Botswana','BW','BWA'),
     ('Brésil','BR','BRA'),
     ('Brunei','BN','BRN'),
     ('Bulgarie','BG','BGR'),
     ('Burkina Faso','BF','BFA'),
     ('Burundi','BI','BDI'),
     ('Cambodge','KH','KHM'),
     ('Cameroun','CM','CMR'),
     ('Canada','CA','CAN'),
     ('Cap-Vert','CV','CPV'),
     ('Chili','CL','CHL'),
     ('Chine','CN','CHN'),
     ('Chypre','CY','CYP'),
     ('Colombie','CO','COL'),
     ('Comores','KM','COM'),
     ('Congo','CG','COG'),
     ('Costa Rica','CR','CRI'),
     ('Côte d’Ivoire','CI','CIV'),
     ('Croatie','HR','HRV'),
     ('Cuba','CU','CUB'),
     ('Danemark','DK','DNK'),
     ('Djibouti','DJ','DJI'),
     ('Dominique','DM','DMA'),
     ('Égypte','EG','EGY'),
     ('Émirats arabes unis','AE','ARE'),
     ('Équateur','EC','ECU'),
     ('Érythrée','ER','ERI'),
     ('Espagne','ES','ESP'),
     ('Estonie','EE','EST'),
     ('Eswatini','SZ','SWZ'),
     ('États-Unis','US','USA'),
     ('Éthiopie','ET','ETH'),
     ('Fidji','FJ','FJI'),
     ('Finlande','FI','FIN'),
     ('France','FR','FRA'),
     ('Gabon','GA','GAB'),
     ('Gambie','GM','GMB'),
     ('Géorgie','GE','GEO'),
     ('Ghana','GH','GHA'),
     ('Grèce','GR','GRC'),
     ('Grenade','GD','GRD'),
     ('Guatemala','GT','GTM'),
     ('Guinée','GN','GIN'),
     ('Guinée-Bissau','GW','GNB'),
     ('Guyana','GY','GUY'),
     ('Haïti','HT','HTI'),
     ('Honduras','HN','HND'),
     ('Hongrie','HU','HUN'),
     ('Inde','IN','IND'),
     ('Indonésie','ID','IDN'),
     ('Irak','IQ','IRQ'),
     ('Iran','IR','IRN'),
     ('Irlande','IE','IRL'),
     ('Islande','IS','ISL'),
     ('Israël','IL','ISR'),
     ('Italie','IT','ITA'),
     ('Jamaïque','JM','JAM'),
     ('Japon','JP','JPN'),
     ('Jordanie','JO','JOR'),
     ('Kazakhstan','KZ','KAZ'),
     ('Kenya','KE','KEN'),
     ('Kirghizistan','KG','KGZ'),
     ('Kiribati','KI','KIR'),
     ('Koweït','KW','KWT'),
     ('Laos','LA','LAO'),
     ('Lesotho','LS','LSO'),
     ('Lettonie','LV','LVA'),
     ('Liban','LB','LBN'),
     ('Liberia','LR','LBR'),
     ('Libye','LY','LBY'),
     ('Liechtenstein','LI','LIE'),
     ('Lituanie','LT','LTU'),
     ('Luxembourg','LU','LUX'),
     ('Madagascar','MG','MDG'),
     ('Malaisie','MY','MYS'),
     ('Malawi','MW','MWI'),
     ('Maldives','MV','MDV'),
     ('Mali','ML','MLI'),
     ('Malte','MT','MLT'),
     ('Maroc','MA','MAR'),
     ('Maurice','MU','MUS'),
     ('Mauritanie','MR','MRT'),
     ('Mexique','MX','MEX'),
     ('Moldavie','MD','MDA'),
     ('Monaco','MC','MCO'),
     ('Mongolie','MN','MNG'),
     ('Monténégro','ME','MNE'),
     ('Mozambique','MZ','MOZ'),
     ('Namibie','NA','NAM'),
     ('Népal','NP','NPL'),
     ('Nicaragua','NI','NIC'),
     ('Niger','NE','NER'),
     ('Nigeria','NG','NGA'),
     ('Norvège','NO','NOR'),
     ('Nouvelle-Zélande','NZ','NZL'),
     ('Oman','OM','OMN'),
     ('Ouganda','UG','UGA'),
     ('Ouzbékistan','UZ','UZB'),
     ('Pakistan','PK','PAK'),
     ('Panama','PA','PAN'),
     ('Paraguay','PY','PRY'),
     ('Pays-Bas','NL','NLD'),
     ('Pérou','PE','PER'),
     ('Philippines','PH','PHL'),
     ('Pologne','PL','POL'),
     ('Portugal','PT','PRT'),
     ('Qatar','QA','QAT'),
     ('République centrafricaine','CF','CAF'),
     ('République dominicaine','DO','DOM'),
     ('République tchèque','CZ','CZE'),
     ('Roumanie','RO','ROU'),
     ('Royaume-Uni','GB','GBR'),
     ('Russie','RU','RUS'),
     ('Rwanda','RW','RWA'),
     ('Sénégal','SN','SEN'),
     ('Serbie','RS','SRB'),
     ('Singapour','SG','SGP'),
     ('Slovaquie','SK','SVK'),
     ('Slovénie','SI','SVN'),
     ('Somalie','SO','SOM'),
     ('Soudan','SD','SDN'),
     ('Sri Lanka','LK','LKA'),
     ('Suède','SE','SWE'),
     ('Suisse','CH','CHE'),
     ('Syrie','SY','SYR'),
     ('Tchad','TD','TCD'),
     ('Thaïlande','TH','THA'),
     ('Togo','TG','TGO'),
     ('Tunisie','TN','TUN'),
     ('Turquie','TR','TUR'),
     ('Ukraine','UA','UKR'),
     ('Uruguay','UY','URY'),
     ('Venezuela','VE','VEN'),
     ('Vietnam','VN','VNM'),
     ('Yémen','YE','YEM'),
     ('Zambie','ZM','ZMB'),
     ('Zimbabwe','ZW','ZWE');


CREATE TABLE utilisateurs (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role VARCHAR(20) NOT NULL, 
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE profils_candidat (
    utilisateur_id INT PRIMARY KEY REFERENCES utilisateurs(id),
    nom VARCHAR(250),
    prenom VARCHAR(250),
    photo TEXT,
    localisation TEXT,
    resume TEXT,
    annees_experience INT DEFAULT 0,
    profil_completed BOOLEAN DEFAULT FALSE
);

CREATE TABLE telephones_candidat (
    id SERIAL PRIMARY KEY,
    candidat_id INT NOT NULL REFERENCES profils_candidat(utilisateur_id),
    telephone TEXT NOT NULL,
    is_phone_principal BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now(),

    CONSTRAINT unique_phone_principal
        UNIQUE (candidat_id, is_phone_principal)
);

CREATE UNIQUE INDEX idx_unique_phone_principal
ON telephones_candidat (candidat_id)
WHERE is_phone_principal = true;

CREATE TABLE entreprises (
     id SERIAL PRIMARY KEY,
     nom TEXT NOT NULL,
     logo TEXT,
     pays_id INT REFERENCES pays(id),
     ville VARCHAR(150),
     siege_social TEXT,
     verifie BOOLEAN DEFAULT FALSE,
     created_at TIMESTAMP DEFAULT NOW(),
     updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE profils_recruteur (
   utilisateur_id INT PRIMARY KEY REFERENCES utilisateurs(id),
   entreprise_id INT NOT NULL REFERENCES entreprises(id),
   photo TEXT,
   fonction TEXT,
   verifie BOOLEAN DEFAULT FALSE
);

CREATE TABLE telephones_recruteur (
    id SERIAL PRIMARY KEY,
    recruteur_id INT NOT NULL REFERENCES profils_recruteur(utilisateur_id),
    telephone TEXT NOT NULL,
    is_phone_principal BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now(),

    CONSTRAINT unique_phone_principal_recruteur
        UNIQUE (recruteur_id, is_phone_principal)
);

CREATE UNIQUE INDEX idx_unique_phone_principal_recruteur
ON telephones_recruteur (recruteur_id)
WHERE is_phone_principal = true;

CREATE TABLE profils_admin (
    utilisateur_id INT PRIMARY KEY REFERENCES utilisateurs(id),
    super_admin BOOLEAN DEFAULT FALSE
);

ALTER TABLE profils_admin
    ADD COLUMN nom VARCHAR(250);

ALTER TABLE profils_admin
    ADD COLUMN prenom VARCHAR(250);

ALTER TABLE profils_admin
    ADD COLUMN photo TEXT;

ALTER TABLE profils_candidat
    ADD COLUMN pays_id INT REFERENCES pays(id),
    ADD COLUMN ville VARCHAR(150),
    ADD COLUMN adresse TEXT;

ALTER TABLE profils_candidat
    DROP COLUMN localisation;

ALTER TABLE pays
    ALTER COLUMN code_iso2 TYPE VARCHAR(2);

ALTER TABLE pays
    ALTER COLUMN code_iso3 TYPE VARCHAR(3);