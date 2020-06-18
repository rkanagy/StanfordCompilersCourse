import java.util.Comparator;

public class CaseBranchClassTagRange {
	private branch caseBranch;
	private Integer minClassTag;
	private Integer maxClassTag;
	
	public CaseBranchClassTagRange(branch caseBranch, Integer minClassTag, Integer maxClassTag) {
		this.caseBranch = caseBranch;
		this.minClassTag = minClassTag;
		this.maxClassTag = maxClassTag;
	}
	
	public branch getCaseBranch() {
		return this.caseBranch;
	}
	
	public Integer getMinClassTag() {
		return this.minClassTag;
	}
	
	public Integer getMaxClassTag() {
		return this.maxClassTag;
	}

	public static class CaseBranchClassTagRangeSortByMinValueDescending implements Comparator<CaseBranchClassTagRange> {
		public int compare(CaseBranchClassTagRange branch1, CaseBranchClassTagRange branch2) {
			//return branch1.getMinClassTag() - branch2.getMinClassTag();
			return branch2.getMinClassTag() - branch1.getMinClassTag();
		}
	}
}
