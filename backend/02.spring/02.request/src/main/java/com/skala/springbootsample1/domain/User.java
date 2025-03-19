// package com.skala.springbootsample1.domain;

// public class User {
//   private long id;
//   private String name;
//   private String email;

//   // 생성자, getter 및 setter
//   public User(long id, String name, String email) {
//       this.id = id;
//       this.name = name;
//       this.email = email;
//   }

//   public long getId() {
//       return id;
//   }

//   public void setId(long id) {
//       this.id = id;
//   }

//   public String getName() {
//       return name;
//   }

//   public void setName(String name) {
//       this.name = name;
//   }

//   public String getEmail() {
//       return email;
//   }

//   public void setEmail(String email) {
//       this.email = email;
//   }
// }

package com.skala.springbootsample1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, toString, equals, hashCode 자동 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
public class User {
    private long id;
    private String name;
    private String email;
}