CREATE TABLE PHONG(
    maPhong TEXT PRIMARY KEY,
    tenPhong TEXT
);
CREATE TABLE TINHTRANG(
    maTinhTrang TEXT PRIMARY KEY,
    tenTinhTrang TEXT
);
CREATE TABLE THIETBI(
    maTB TEXT CONSTRAINT PK_TB PRIMARY KEY,
    tenTB TEXT,
    maPhong TEXT NOT NULL,
    maTinhTrang TEXT NOT NULL,
    ngayNhapTB DATE DEFAULT CURRENT_TIMESTAMP,
    hanBaoTri DATE DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE THANHLY(
    maPhieu TEXT CONSTRAINT PK_TL PRIMARY KEY,
    maTB TEXT NOT NULL,
    ngayTL DATE DEFAULT CURRENT_TIMESTAMP,
    giaTL INTEGER CHECK(giaTL > 0),
    CONSTRAINT FK_TL FOREIGN KEY(maTB) REFERENCES THIETBI(maTB)
);
CREATE TABLE SUACHUA(
    maSC TEXT PRIMARY KEY,
    maTB TEXT NOT NULL,
    chitiet TEXT DEFAULT 'no information',
    giaSC INTEGER CHECK(giaSC > 0),
    CONSTRAINT FK_SC FOREIGN KEY(maTB) REFERENCES THIETBI(maTB)
);
ALTER TABLE THIETBI ADD CONSTRAINT FK_TT FOREIGN KEY(maTinhTrang) REFERENCES TINHTRANG(maTinhTrang);
ALTER TABLE THIETBI ADD CONSTRAINT FK_PHONG FOREIGN KEY(maPhong) REFERENCES PHONG(maPhong);
ALTER TABLE THANHLY ADD CONSTRAINT CK_MAPHIEU CHECK(maPhieu ~* 'HD[0-9]+');
ALTER TABLE TINHTRANG ADD CONSTRAINT CK_MATT CHECK(maTinhTrang ~* 'TT[0-9]+');
ALTER TABLE SUACHUA ADD CONSTRAINT CK_SUACHUA CHECK(maSC ~* 'SC[0-9]+');
INSERT INTO TINHTRANG VALUES('TT1', 'Tốt');
INSERT INTO TINHTRANG VALUES('TT2', 'Lỗi');
INSERT INTO TINHTRANG VALUES('TT3', 'Hư');
INSERT INTO PHONG VALUES('A1', 'Phòng khám A1');
INSERT INTO PHONG VALUES('A2', 'Phòng khám A2');
INSERT INTO PHONG VALUES('A3', 'Phòng khám A3');