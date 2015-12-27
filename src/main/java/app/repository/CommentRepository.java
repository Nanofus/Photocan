package app.repository;
 
import app.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.FileObject;
import java.util.List;
 
public interface CommentRepository extends JpaRepository<Comment, Long> {
     public List<Comment> findByImageId(Long imageId);
}