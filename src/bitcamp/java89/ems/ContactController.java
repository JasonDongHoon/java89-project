package bitcamp.java89.ems;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactController {
  private String filename = "contactv1.5.data";
  private ArrayList<Contact> list;
  private boolean changed;
  private Scanner keyScan;

  public ContactController(Scanner keyScan) {
    list = new ArrayList<Contact>();
    this.keyScan = keyScan;

    this.load(); 
  }

  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Contact>)in.readObject();
      
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {}
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);
    
    changed = false;

    out.close();
    out0.close();
  }

  public void service() {
    loop:
    while (true) {
      System.out.print("연락처관리> ");
      String command = keyScan.nextLine().toLowerCase();

      try {
        switch (command) {
        case "add": this.doAdd(); break;
        //case "list": this.doList(); break;
        //case "view": this.doView(); break;
        //case "delete": this.doDelete(); break;
        //case "update": this.doUpdate(); break;
        case "main":
          break loop;
        default:
          System.out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        System.out.println("실행 중 오류가 발생했습니다.");
      } // try
    } // while
  }

/*
  private void doList() {
    for (Contact contact : list) {
      System.out.printf("%s,%s,%s,%s,%s,%s,%d,%s\n",
        contact.userId,
        contact.password,
        contact.name,
        contact.tel,
        contact.email,
        ((contact.working)?"yes":"no"),
        contact.birthYear,
        contact.school);
    }
  }

  private void doUpdate() {
    System.out.print("변경할 학생의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Contact oldContact = list.get(index);

    // 새 학생 정보를 입력 받는다.
    Contact contact = new Contact();
    System.out.print("암호(예:1111)? ");
    contact.password = this.keyScan.nextLine();

    System.out.printf("이름(%s)? ", oldContact.name);
    contact.name = this.keyScan.nextLine();

    System.out.printf("전화(%s)? ", oldContact.tel);
    contact.tel = this.keyScan.nextLine();

    System.out.printf("이메일(%s)? ", oldContact.email);
    contact.email = this.keyScan.nextLine();

    System.out.print("재직중(y/n)? ");
    contact.working = (this.keyScan.nextLine().equals("y")) ? true : false;

    while (true) {
      try {
        System.out.printf("태어난해(%d)? ", oldContact.birthYear);
        contact.birthYear = Integer.parseInt(this.keyScan.nextLine());
        break;
      } catch (Exception e) {
        System.out.println("정수 값을 입력하세요.");
      }
    }

    System.out.printf("최종학교(%s)? ", oldContact.school);
    contact.school = this.keyScan.nextLine();

    System.out.print("저장하시겠습니까(y/n)? ");
    if (keyScan.nextLine().toLowerCase().equals("y")) {
      contact.userId = oldContact.userId;
      list.set(index, contact);
      changed = true;
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
  }
*/
  private void doAdd() {
    while (true) {
      Contact contact = new Contact();
      System.out.print("이름(예:홍길동)? ");
      contact.setName(this.keyScan.nextLine());

      System.out.print("직위(예:대리)? ");
      contact.setPosition(this.keyScan.nextLine());

      System.out.print("전화(예:010-1111-2222)? ");
      contact.setTel(this.keyScan.nextLine());

      System.out.print("이메일(예:hong@test.com)? ");
      contact.setEmail(this.keyScan.nextLine());

      list.add(contact);
      changed = true;

      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    } // while
  }
/*
  private void doView() {
    System.out.print("학생의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Contact contact = list.get(index);

    System.out.printf("아이디: %s\n", contact.userId);
    System.out.printf("암호: (***)\n");
    System.out.printf("이름: %s\n", contact.name);
    System.out.printf("전화: %s\n", contact.tel);
    System.out.printf("이메일: %s\n", contact.email);
    System.out.printf("재직중: %s\n", (contact.working) ? "Yes" : "No");
    System.out.printf("태어난 해: %d\n", contact.birthYear);
    System.out.printf("학교: %s\n", contact.school);
  }

  private void doDelete() {
    System.out.print("삭제할 학생의 인덱스? ");
    int index = Integer.parseInt(keyScan.nextLine());
    Contact deletedContact = list.remove(index);
    changed = true;
    System.out.printf("%s 학생 정보를 삭제하였습니다.\n", deletedContact.userId);
  }
*/
}
