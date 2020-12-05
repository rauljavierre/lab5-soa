## How to use it

1. Rename .env_changeme to .env and complete it with all your environment variables (Twitter credentials, Docker configurations...)
2. Execute "sudo bash up.sh"
3. Access to http://localhost:${SPRING_HOST_PORT} from the host using a browser
4. Access to mongodb://${MONGO_INITDB_ROOT_USERNAME}:${MONGO_INITDB_ROOT_PASSWORD}@localhost:${MONGODB_HOST_PORT} from the host using a Mongo Client to check the saved documents