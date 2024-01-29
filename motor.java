#include <Arduino.h>

// Motor control pins
const int motorPin1 = 2;  // Motor driver input 1
const int motorPin2 = 3;  // Motor driver input 2
const int enablePin = 9;  // Motor driver enable pin

// Compression sensor pin
const int compressionSensorPin = A0;

// Compression threshold (adjust based on your sensor)
const int compressionThreshold = 500;

void setup() {
  Serial.begin(9600);

  // Motor control pins setup
  pinMode(motorPin1, OUTPUT);
  pinMode(motorPin2, OUTPUT);
  pinMode(enablePin, OUTPUT);

  // Compression sensor pin setup
  pinMode(compressionSensorPin, INPUT);

  // Initialize motor driver
  digitalWrite(motorPin1, LOW);
  digitalWrite(motorPin2, LOW);
  analogWrite(enablePin, 0);
}

void loop() {
  // Check compression level
  int compressionLevel = analogRead(compressionSensorPin);

  // Print compression level (for debugging)
  Serial.println("Compression Level: " + String(compressionLevel));

  // Check if compression level is below the threshold
  if (compressionLevel < compressionThreshold) {
    // Compress the sample by running the motor
    compressSample();
  } else {
    // Stop the motor if the compression level is reached
    stopMotor();
  }
}

void compressSample() {
  // Run the motor in one direction
  digitalWrite(motorPin1, HIGH);
  digitalWrite(motorPin2, LOW);
  analogWrite(enablePin, 255);  // Adjust speed based on your motor and requirements
}

void stopMotor() {
  // Stop the motor
  digitalWrite(motorPin1, LOW);
  digitalWrite(motorPin2, LOW);
  analogWrite(enablePin, 0);
}