Change Calculator by Omoda
---------

Running by:

With C:\WINDOWS\system32\cmd.exe

cd backend
mvn spring-boot:run

and

cd frontend
npm i -f && npm run start

---------
Or running by:

With Kubernetes (Docker Desktop, https://www.docker.com/products/docker-desktop/)

kubectl apply -f deployment.yaml

Images pulled from;
https://hub.docker.com/repository/docker/edwinitdekker/omoda-frontend/general
https://hub.docker.com/repository/docker/edwinitdekker/omoda-backend/general