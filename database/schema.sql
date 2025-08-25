/*==============================================================*/
/* Table : Association_prod_panier                              */
/*==============================================================*/
create table Association_prod_panier (
   id_panier            INT4                 null,
   id_produit           INT8                 null
);

/*==============================================================*/
/* Index : ASSOCIATION_5_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_5_FK on Association_prod_panier (
id_panier
);

/*==============================================================*/
/* Table : Commande                                             */
/*==============================================================*/
create table Commande (
   id_pers              INT8                 null,
   id_commande          INT4                 not null,
   id_paiement          INT4                 null,
   date                 DATE                 null,
   statut               VARCHAR(254)         null,
   montant_total        NUMERIC              null,
   constraint PK_COMMANDE primary key (id_commande)
);

/*==============================================================*/
/* Index : ASSOCIATION_1_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_1_FK on Commande (
id_pers
);

/*==============================================================*/
/* Table : LigneCommande                                        */
/*==============================================================*/
create table LigneCommande (
   id_commande          INT4                 null,
   id_ligne             INT4                 not null,
   id_produit           INT8                 null,
   prixUnitaire         NUMERIC              null,
   quantite             INT4                 null,
   constraint PK_LIGNECOMMANDE primary key (id_ligne)
);

/*==============================================================*/
/* Index : ASSOCIATION_3_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_3_FK on LigneCommande (
id_commande
);

/*==============================================================*/
/* Table : Paiement                                             */
/*==============================================================*/
create table Paiement (
   id_paiement          INT4                 not null,
   date                 DATE                 null,
   montant              NUMERIC              null,
   statut               VARCHAR(254)         null,
   methode              VARCHAR(254)         null,
   constraint PK_PAIEMENT primary key (id_paiement)
);

/*==============================================================*/
/* Table : Panier                                               */
/*==============================================================*/
create table Panier (
   id_panier            INT4                 not null,
   date                 DATE                 null,
   total                NUMERIC              null,
   constraint PK_PANIER primary key (id_panier)
);

/*==============================================================*/
/* Table : Personne                                             */
/*==============================================================*/
create table Personne (
   id_pers              INT8                 not null,
   id_panier            INT4                 null,
   nom                  VARCHAR(254)         null,
   prenom               VARCHAR(254)         null,
   role                 VARCHAR(254)         null,
   adresse              VARCHAR(254)         null,
   telephone            VARCHAR(254)         null,
   email                VARCHAR(254)         null,
   constraint PK_PERSONNE primary key (id_pers)
);

/*==============================================================*/
/* Index : ASSOCIATION_2_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_2_FK on Personne (
id_panier
);

/*==============================================================*/
/* Table : Produit                                              */
/*==============================================================*/
create table Produit (
   id_categorie         INT4                 null,
   id_produit           INT8                 not null,
   libelle              VARCHAR(254)         null,
   description          VARCHAR(254)         null,
   stock                NUMERIC              null,
   prix                 NUMERIC              null,
   image                VARCHAR(254)         null,
   constraint PK_PRODUIT primary key (id_produit)
);

/*==============================================================*/
/* Index : ASSOCIATION_7_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_7_FK on Produit (
id_categorie
);

/*==============================================================*/
/* Table : categorie                                            */
/*==============================================================*/
create table categorie (
   id_categorie         INT4                 not null,
   description          VARCHAR(254)         null,
   nom                  VARCHAR(254)         null,
   constraint PK_CATEGORIE primary key (id_categorie)
);

alter table Association_prod_panier
   add constraint FK_ASSOCIAT_ASSOCIATI_PANIER foreign key (id_panier)
      references Panier (id_panier)
      on delete restrict on update restrict;

alter table Association_prod_panier
   add constraint FK_ASSOCIAT_ASSOCIATI_PRODUIT foreign key (id_produit)
      references Produit (id_produit)
      on delete restrict on update restrict;

alter table Commande
   add constraint FK_COMMANDE_ASSOCIATI_PERSONNE foreign key (id_pers)
      references Personne (id_pers)
      on delete restrict on update restrict;

alter table Commande
   add constraint FK_COMMANDE_ASSOCIATI_PAIEMENT foreign key (id_paiement)
      references Paiement (id_paiement)
      on delete restrict on update restrict;

alter table LigneCommande
   add constraint FK_LIGNECOM_ASSOCIATI_COMMANDE foreign key (id_commande)
      references Commande (id_commande)
      on delete restrict on update restrict;

alter table LigneCommande
   add constraint FK_LIGNECOM_ASSOCIATI_PRODUIT foreign key (id_produit)
      references Produit (id_produit)
      on delete restrict on update restrict;

alter table Personne
   add constraint FK_PERSONNE_ASSOCIATI_PANIER foreign key (id_panier)
      references Panier (id_panier)
      on delete restrict on update restrict;

alter table Produit
   add constraint FK_PRODUIT_ASSOCIATI_CATEGORI foreign key (id_categorie)
      references categorie (id_categorie)
      on delete restrict on update restrict;