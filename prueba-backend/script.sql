
-- Datos iniciales
INSERT INTO app_user (full_name, email, phone) VALUES
    ('Usuario Único', 'user@example.com', '+57 3000000000');

INSERT INTO wallet (user_id, balance_cop) VALUES
    (1, 500000.00);

INSERT INTO fund_category (code, name) VALUES
                                           ('FPV','Fondo Voluntario de Pensión'),
                                           ('FIC','Fondo de Inversión Colectiva');

-- Fondos del enunciado
INSERT INTO fund (name, category_id, min_amount_cop) VALUES
                                                         ('FPV_BTG_PACTUAL_RECAUDADORA', (SELECT id FROM fund_category WHERE code='FPV'),  75000),
                                                         ('FPV_BTG_PACTUAL_ECOPETROL',   (SELECT id FROM fund_category WHERE code='FPV'), 125000),
                                                         ('DEUDAPRIVADA',                (SELECT id FROM fund_category WHERE code='FPV'),  50000),
                                                         ('FDO-ACCIONES',                (SELECT id FROM fund_category WHERE code='FIC'), 250000),
                                                         ('FPV_BTG_PACTUAL_DINAMICA',    (SELECT id FROM fund_category WHERE code='FIC'), 100000);
