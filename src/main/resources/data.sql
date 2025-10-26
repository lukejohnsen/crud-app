INSERT INTO client (
    company_name,
    website_uri,
    phone_number,
    street_address,
    city,
    state,
    zip_code
) VALUES (
             'Aquent LLC',
             'https://aquent.com',
             '555-0100',
             '711 Boylston St',
             'Boston',
             'MA',
             '02116'
         ), (
             'Tech Solutions Inc',
             'https://techsolutions.example.com',
             '555-0200',
             '456 Market St',
             'San Francisco',
             'CA',
             '94103'
         ), (
             'Creative Agency Co',
             'https://creativeagency.example.com',
             '555-0300',
             '789 Design Ave',
             'Austin',
             'TX',
             '78701'
         );

INSERT INTO person (
    first_name,
    last_name,
    email_address,
    street_address,
    city,
    state,
    zip_code,
    client_id
) VALUES (
             'John',
             'Smith',
             'fake1@aquent.com',
             '123 Any St.',
             'Asheville',
             'NC',
             '28801',
             1
         ), (
             'Jane',
             'Smith',
             'fake2@aquent.com',
             '123 Any St.',
             'Asheville',
             'NC',
             '28801',
             2
         );
