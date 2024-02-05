create table task_outbox(
    id uuid primary key,
    task_id uuid not null references task(id),
    created_at timestamp with time zone not null,
    sent_to_bus boolean not null
);