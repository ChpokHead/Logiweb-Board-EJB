create table location
(
    id bigserial not null
        constraint location_pkey
            primary key,
    name varchar(255)
);

alter table location owner to chpok;

create table truck
(
    id bigserial not null
        constraint truck_pkey
            primary key,
    capacity smallint,
    drivers_shift smallint,
    reg_number varchar(255),
    location_id bigint
        constraint truck_location__fk
            references location
            on update cascade,
    status integer
);

alter table truck owner to chpok;

