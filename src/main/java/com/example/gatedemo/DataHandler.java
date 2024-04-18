package com.example.gatedemo;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import org.springframework.stereotype.Service;
import ai.onnxruntime.*;
import java.nio.*;


import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DataHandler {

    OrtSession session;
    OrtEnvironment env;

    public double getResultFromXOR(int input1, int input2) throws OrtException
    {
        env = OrtEnvironment.getEnvironment();
        session = env.createSession("src/main/resources/xor.onxx");

    // Create input tensor
        float[] inputArray = new float[]{input1, input2};
        long[] inputShape = new long[]{1, 2};
        FloatBuffer inputData = FloatBuffer.wrap(inputArray);
        OnnxTensor inputTensor = OnnxTensor.createTensor(env, inputData, inputShape);

        // Run inference
        OrtSession.Result output = session.run(Collections.singletonMap("dense_2_input", inputTensor));

        // Get output tensor
        OnnxTensor outputTensor = (OnnxTensor) output.get(0);

        // Process output tensor
        float[][] outputArray = (float[][]) outputTensor.getValue();
        // Process the output data as needed

        // Close resources
        inputTensor.close();
        outputTensor.close();
        session.close();
        env.close();
        System.out.println(outputArray[0][0]);
        return (double) outputArray[0][0];
    }

}
