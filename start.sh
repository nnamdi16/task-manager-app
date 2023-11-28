#!/bin/bash
# Define ports for services
FRONTEND_PORT=3000
TASK_MANAGER_SERVICE_PORT=8081

# Define an array of port numbers you want to stop
ports=("$FRONTEND_PORT" "$TASK_MANAGER_SERVICE_PORT")  # Add your port numbers here

# Loop through the array and attempt to stop processes on each port
for port in "${ports[@]}"; do
    echo "Stopping processes on port $port..."
    lsof -t -i :"$port" | xargs kill -9
done

echo "All specified ports have been stopped."

# Start the frontend on port 8080
cd frontend || exit
npm install
npm run dev -- --port 3000 &
cd ..

# Start Task Manager  Service on port 8081
cd backend || exit
./mvnw spring-boot:run -Dserver.port=$TASK_MANAGER_SERVICE_PORT &
cd ..


