import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.util.*;

public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
  List list = new ArrayList<>();

    public UploadFileResponse(String fileName, String fileDownloadUri) throws IOException {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
      list= readFile(this.fileDownloadUri);
    }


  @Override
  public String toString() {
    return "UploadFileResponse{" +
      "fileName='" + fileName + '\'' +
      ", fileDownloadUri='" + fileDownloadUri + '\'' +
      ", list=" + list +
      '}';
  }

  public List readFile(String file) throws IOException {
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
    String f;
    int count=0;
    StringBuffer strBuffer = new StringBuffer();
    Map<String ,Integer> map = new HashMap<String,Integer>();
    while ((f=bufferedReader.readLine())!=null){
      StringTokenizer tokenizer = new StringTokenizer(f, " \t\n\r,:-.");
      while(tokenizer.hasMoreTokens()) {
        String s = tokenizer.nextToken();
        if(s.length()>2) {
          strBuffer.append(s);
          int cnt = map.get(s) != null ? map.get(s) : 0;
          cnt++;
          map.put(s, cnt);
        }
      }
    }
// создаем список , где каждый элемент списка - набор ключ/значение (EntrySet - внутренее пространство имен, которое дает доступ к паре ключ/значение )
    ArrayList list = new ArrayList(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
        return b.getValue() - a.getValue();
      }
    });
    ArrayList newList = new ArrayList();
    if(list.size()>10){
      for(int i = 0; i<10; i++){
        newList.add(String.valueOf(list.get(i)));

      }
      return newList;
    }
    ArrayList arrayList = new ArrayList();

    for(Object s: list){
      String r = String.valueOf(s);
      arrayList.add(r);
      System.out.println(s.toString());
    }
    return  arrayList;

  }

}

