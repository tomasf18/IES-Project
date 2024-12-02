import json
import matplotlib.pyplot as plt

# Initialize a dictionary to store data by sensor
data_by_sensor = {}

# Read and parse the file
file_path = "consumed_messages.txt"
with open(file_path, "r") as file:
    for line in file:
        # Remove leading/trailing whitespace and parse the JSON data
        data_list = json.loads(line.strip()[2:-1])  # Strip the b' and ' characters
        
        # Iterate over the list of sensor data
        for data in data_list:
            sensor_id = data["sensor_id"] 
            
            # Initialize data lists for the sensor if not already present
            if sensor_id not in data_by_sensor:
                data_by_sensor[sensor_id] = {
                    "heart_rate": [],
                    "respiratory_rate": [],
                    "body_temperature": []
                }
            
            # Append the data for the sensor
            data_by_sensor[sensor_id]["heart_rate"].append(data["heart_rate"])
            data_by_sensor[sensor_id]["respiratory_rate"].append(data["respiratory_rate"])
            data_by_sensor[sensor_id]["body_temperature"].append(data["body_temperature"])

# Create subplots
fig, axs = plt.subplots(3, 1, figsize=(12, 12))

# Define a colormap for distinguishing sensors
colors = plt.cm.get_cmap("tab10", len(data_by_sensor))  # Get a colormap with enough distinct colors

# Plot the data for each sensor
for idx, (sensor_id, sensor_data) in enumerate(data_by_sensor.items()):
    color = colors(idx)
    
    # Plot heart rate
    axs[0].plot(sensor_data["heart_rate"], label=f'Sensor {sensor_id}', color=color)
    
    # Plot respiratory rate
    axs[1].plot(sensor_data["respiratory_rate"], label=f'Sensor {sensor_id}', color=color)
    
    # Plot body temperature
    axs[2].plot(sensor_data["body_temperature"], label=f'Sensor {sensor_id}', color=color)

# Configure each subplot
axs[0].set_title('Heart Rate')
axs[0].set_xlabel('Time')
axs[0].set_ylabel('Heart Rate (bpm)')
axs[0].legend()

axs[1].set_title('Respiratory Rate')
axs[1].set_xlabel('Time')
axs[1].set_ylabel('Respiratory Rate (rpm)')
axs[1].set_ylim(0, 30)
axs[1].legend()

axs[2].set_title('Body Temperature')
axs[2].set_xlabel('Time')
axs[2].set_ylabel('Body Temperature (°C)')
axs[2].set_ylim(35, 40)
axs[2].legend()

# Save the plot to a file
plt.tight_layout()
plt.savefig("output_plot.png")
