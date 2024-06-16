package com.hasanalmunawr.Ebook_Store.token;


import com.hasanalmunawr.Ebook_Store.user.Auditable;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tokens")
public class TokenEntity extends Auditable {

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean isRevoked;
    public boolean isExpired;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity user;


    public static int binarySearch(int[] arr, int key) {
        Arrays.stream(arr).sorted();
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int[] sort(int[] arr) {
        return IntStream.of(arr).sorted().toArray();
    }

    public static int[] sortManual(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < newArr.length - 1; i++) {
            for (int j = 0; j < newArr.length - 1 - i; j++) {
                if (newArr[j] > newArr[j + 1]) {
                    int temp = newArr[j];
                    newArr[j] = newArr[j + 1];
                    newArr[j + 1] = temp;
                }
            }
        }
        return newArr;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,3,4,2,6,9,2,9};

        System.out.print(sortManual(arr));
    }

}
