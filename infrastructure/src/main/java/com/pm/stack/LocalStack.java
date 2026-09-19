package com.pm.stack;

import software.amazon.awscdk.*;

import java.util.Stack;

public class LocalStack extends Stack {

	public LocalStack(
					final App scope,
					final String id,
					final StackProps props) {

		super();
	}

	public static void main(String[] args) {

		App app = new App(AppProps.builder().outdir("./cdk.out").build());

		StackProps props = StackProps.builder()
						.synthesizer(new BootstraplessSynthesizer())
						.build();

		new LocalStack(app, "localstack", props);

		app.synth();

		System.out.println("App synthesizing in progress...");
	}
}
