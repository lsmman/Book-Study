package EffectiveJava.item47;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DataReader {
    Post[] postList;

    private void loadData(){
        /* 게시글을 불러오는 로직 */
        this.postList = new Post[] {
                new Post("first", "나", "hey"),
                new Post("second", "너", "ho"),
                new Post("third", "우리", "hu")};
    }

    public Post[] getPostList_Array(){
        return postList.clone();
    }

    public Stream<Post> getPostList_Stream(){
        return Stream.of(postList);
    }

    public Iterable<Post> getPostList_Iterable(){
        return Arrays.asList(postList);
    }

    public List<Post> getPostList(){

        return new ArrayList<>(Arrays.asList(postList));
    }

    public static void main(String[] args) {
        DataReader dataReader = new DataReader();

        // Stream 객체 반환 후 for문이나 forEach 문에 쓰려고 하면?
        Stream<Post> postList_stream = dataReader.getPostList_Stream();
        
//        for (String s : postList_stream::iterator) {
//
//        }
        
        for (int i = 0; i < 3; i++) {
            
//            postList_stream
//            System.out.println(postList_stream);
        }

        // forEach
//        iter

        // *****************************************************

        // Stream 반환 후 -> Iterable
        Stream<Post> postList_stream2 = dataReader.getPostList_Stream();
        for (Post post : Adapters.iterableOf(postList_stream2)) {
            System.out.println(post);
        }

        // Iterable 반환 후 -> Stream
        Iterable<Post> postList_iterable = dataReader.getPostList_Iterable();
        
        Adapters.streamOf(postList_iterable)
                .forEach(System.out::println);

        List<Post> postList = dataReader.getPostList();
        Stream<Post> stream = postList.stream();
        Iterator<Post> iterator = postList.iterator();
        
//        stream.collect().toString();
        for (Post post : postList) {
            
        }                
        


    }

    class Post{
        String title;
        String author;
        String content;

        public Post(String title, String author, String content) {
            this.title = title;
            this.author = author;
            this.content = content;
        }

        Post() {}

        @Override
        public String toString() {
            return "Post{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
