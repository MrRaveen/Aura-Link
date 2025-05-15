package com.example.UserProfile.configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {
    /**
     * Configures a Bucket4j bucket with a capacity of 5 tokens that refills at a rate of 5 tokens per minute.
     * Each token represents the capacity to handle one request.
     */

    /*
    * It creates a bucket with a capacity of five tokens, which refills at a rate of five tokens per minute.
    * This configuration will be used to limit the number of API requests per user.
    * */
    @Bean
    public Bucket bucket() {
        // Define the bandwidth with a limit of 5 tokens, refilled every minute
        Bandwidth limit = Bandwidth.classic(100000, Refill.greedy(100000, Duration.ofMinutes(1)));
        return Bucket4j.builder().addLimit(limit).build();

        /*
        * Refill methods
        *Greedy -->
        * This type of refill greedily regenerates tokens manner,
        * it tries to add the tokens to the bucket as soon as possible.
        *  For example refill "10 tokens per 1 second" adds 1 token per every 100 milliseconds,
        * in other words, the refill will not wait 1 second to regenerate a
        * whole bunch of 10 tokens. The three refills below do refill of tokens with the same speed:
        *
        * Intervally -->
        * This type of refill regenerates tokens in an interval manner.
        * "Interval" in opposite to "greedy" will wait
        * until the whole period will be elapsed before regenerating the whole amount of tokens.
        *
        * IntervallyAligned -->
        * In addition to Interval it is possible to specify the time when the first refill should happen.
        * This type can be used to configure clear interval boundary i.e. start of the second, minute, hour, day.
        * (This ensures that refills occur at predictable times, making it useful for synchronized rate-limiting.)
        * */
    }
}
